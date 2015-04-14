FROM nginx
MAINTAINER Lucas Martins - TimeoutZero

COPY default.conf /etc/nginx/conf.d/default.conf
COPY web/builds/dev/ /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]