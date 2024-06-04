FROM postgres:16.1

ENV POSTGRES_DB kopportunities-db
ENV POSTGRES_USER aa8771fd-f5c0-4317-862a-6b554173cec7
ENV POSTGRES_PASSWORD e7128a2c-e4e9-4a33-b72a-dcf1ead6d105

COPY schema.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]