import React, { useState } from 'react';
import Editor from './Editor'
import {v4 as uuidv4} from 'uuid'
import { Routes,Route,Navigate} from 'react-router-dom';
const App = () => {
  
return(

  <>
   
  <Routes>
    <Route path="/" element={<Navigate to={`/documents/${uuidv4()}`}/>} />
      <Route path="/documents/:id" element={<Editor/>}></Route>
  </Routes>
  
  </>
)


};

export default App;