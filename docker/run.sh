#!/bin/sh

export RUST_LOG=info
cd /opt/api/
RUST_LOG=info mvn spring-boot:run
