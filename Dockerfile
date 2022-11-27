FROM openjdk:11

#set timezone
USER root

ENV TZ=Asia/Seoul

WORKDIR app
COPY build/libs/*.jar app.jar
COPY /src/main/resources/jsons/sample_movie.json /app/jsons/sample_movie.json

# environments
#ENV SPRING_PROFILES_ACTIVE $APP_PROFILES
ENV PGID=1000
ENV DEFAULT_OPTS="-D_hook_async_enabled=true -noverify -D_hook_webflux_service_enabled=true -Djdk.attach.allowAttachSelf=true -Dtx_error_on_mono_cancel_enable=false"
ENV JAVA_OPTS="$JAVA_OPTS -Xmx1000m"

# expose port
EXPOSE 8080

# run application
ENTRYPOINT java $DEFAULT_OPTS $JAVA_OPTS -jar /app/app.jar
