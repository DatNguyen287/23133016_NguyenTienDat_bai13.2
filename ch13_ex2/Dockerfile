# Dùng Tomcat chính thức từ Docker Hub
FROM tomcat:9.0-jdk17

# Xóa webapp mặc định (ROOT)
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy file .war của bạn thành ROOT.war để Tomcat tự deploy
COPY war-exports/ch13_ex2.war /usr/local/tomcat/webapps/ROOT.war

# Expose cổng 8080 (Render sẽ tự map qua $PORT)
EXPOSE 8080

# Chạy Tomcat
CMD ["catalina.sh", "run"]
