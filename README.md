# spring_boot_email_auth
spring project - 이메일 인증 기능

### 기술 스택
- Web서버 : nginx
- DB서버 : mariaDB
- IDE : intelliJ
- Java, Spring Boot, JPA, Spring Mail

### nginx config 설정 파일 내용
```
server {
        listen       80;
        server_name  localhost;
        # nginx root폴더
        root /opt/homebrew/etc/nginx/html;

        location / {
            try_files $uri $uri/ =404; 
        }

        location /api {
            rewrite ^/api(.*)$ $1 break;
            proxy_pass         http://localhost:8080/; 
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection 'upgrade';
            proxy_set_header Host $host;

        }

        error_page   500 502 503 504  /50x.html;

        location = /50x.html {
            root   html;
        }
    }
```
