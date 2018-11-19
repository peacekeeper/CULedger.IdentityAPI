#!/bin/bash

export RUST_LOG=info
cd /opt/api/
mvn spring-boot:run
