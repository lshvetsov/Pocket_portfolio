FROM openjdk:11

ARG VERSION
ENV VERSION $VERSION
ENV JAVA_OPTS "-Dfile.encoding=UTF-8"

EXPOSE 8085

USER root

RUN mkdir -p /opt/app && echo "Europe/Moscow"> /etc/timezone

COPY ./entry-point.sh /opt/app/
COPY ./target/*.jar /opt/app/

RUN useradd --create-home --shell /bin/bash owner \
    && chown --recursive owner /opt/app \
    && chmod +x /opt/app/entry-point.sh \
    && chmod --recursive "g+rwX" /opt/app

USER owner

WORKDIR /opt/app
ENTRYPOINT ["sh","./entry-point.sh"]