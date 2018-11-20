#!/bin/bash

export RUST_LOG=debug
cd /opt/api/
RUST_LOG=debug mvn spring-boot:run
