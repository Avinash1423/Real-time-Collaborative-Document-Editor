import { useCallback,useEffect,useRef} from "react";
import Quill from "quill";
import "quill/dist/quill.snow.css";
//import SockJs from "sockjs-client"
import {Client} from '@stomp/stompjs'
import {useParams} from "react-router-dom";

const Editor = () => {

const initialized=useRef(false);
const quillref=useRef(null);
const {id:docxId}=useParams();

  
 const call=useCallback(
  (el)=>{

    if(!el) return;

    if(!initialized.current){

    quillref.current = new  Quill(el,{theme:'snow'})

    initialized.current=true;
    
    }
  }
  ,[]
)

//LOAD
useEffect(()=>{
  const fetchData=async()=>{
const res= await fetch(`http://localhost:5000/expose/${docxId}`)
const data= await res.json();
console.log("LOAD"+data.docx); //load
quillref.current.updateContents(data.docx);
  }
  fetchData();
},[])


 useEffect(()=>{
  
console.log("🔵 Creating SockJS socket...");

 //const socket=new SockJs(http://localhost:5000");

 //** 
 //to use sockJs u need define:{global:{}} in vite.config in that case u do not need brokerURL:"http://localhost:5000/ws",
 //but need   // webSocketFactory:()=>socket,
 //**

 // stompClient=Stomp.over(socket); deprecated

   const stompClient=new Client({
   brokerURL:"http://localhost:5000/ws",
   // webSocketFactory:()=>socket,
   debug:(str)=>{console.log("STOMP DEBUG:", str)},

    
 });
  stompClient.onConnect=()=>{


  const myId=Math.random().toString(36).slice(2);

   //SEND
   quillref.current.on("text-change",(delta,oldDelta,source)=>{
    if (source !== "user") return;
   stompClient.publish({destination:"/app/server",body: JSON.stringify({sender: myId , delta: delta}),headers:{docxId:docxId},});

  });


   // UPDATE REF
   quillref.current.on("text-change",(delta,oldDelta,source)=>{
    if (source !== "user") return;
   stompClient.publish({destination:"/app/redis",body: JSON.stringify({docx: quillref.current.getContents()}),headers:{docxId:docxId},});

  });


   //RECEIVE
    stompClient.subscribe(`/queue/${docxId}`,(deltaString)=>{
     const payload=JSON.parse(deltaString.body)

     if(payload.sender===myId) return;
     quillref.current.updateContents(payload.delta);

   } );

  }
  


  stompClient.activate();

  return()=> stompClient.deactivate()

 },[])

  return(
<div>
   <div  ref={call}></div>
   </div>
  )
};

export default Editor;
