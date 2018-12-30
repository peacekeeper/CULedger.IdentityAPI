#!/bin/sh

export RUST_LOG=info
cd /opt/culedger-identityapi/
RUST_LOG=info mvn spring-boot:run
