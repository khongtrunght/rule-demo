FROM node:10.6.0-alpine AS builder
WORKDIR /app

COPY . .

RUN npm install && \
    npm run prod


FROM nginx:alpine

COPY --from=builder /app/dist/ /usr/share/nginx/html/iui/holmes/
COPY /nginx.conf /etc/nginx/conf.d/default.conf