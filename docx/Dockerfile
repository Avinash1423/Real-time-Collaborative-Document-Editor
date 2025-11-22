#build
FROM node:18 AS build
COPY package-lock.json package.json ./
RUN npm install
COPY . .
RUN npm run  build

#run
FROM nginx:alpine
COPY --from=build dist /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx","-g","daemon off;"]