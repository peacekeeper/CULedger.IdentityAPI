#!/bin/sh

export RUST_LOG=info

if [ ! -z ${VCX_INSTITUTION_DID_SEED} ]; then
	/opt/culedger-identityapi/docker/provision.sh
fi

cd /opt/sovrin/
sed -i "s,\"genesis_path\": \"<CHANGE_ME>\",\"genesis_path\": \"/opt/sovrin/${VCX_GENESIS_PATH}\",g" ./vcxconfig.json
sed -i "s,\"institution_logo_url\": \"<CHANGE_ME>\",\"institution_logo_url\": \"${VCX_INSTITUTION_LOGO_URL}\",g" ./vcxconfig.json
sed -i "s,\"institution_name\": \"<CHANGE_ME>\",\"institution_name\": \"${VCX_INSTITUTION_NAME}\",g" ./vcxconfig.json

cd /opt/culedger-identityapi/
RUST_LOG=info mvn spring-boot:run
