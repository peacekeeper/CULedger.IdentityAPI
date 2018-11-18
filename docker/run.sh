#!/bin/bash

export RUST_LOG=debug
cd /opt/api/
mvn spring-boot:run
