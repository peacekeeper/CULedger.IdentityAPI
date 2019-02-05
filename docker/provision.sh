#!/bin/sh

rm /opt/sovrin/vcxconfig.json
rm -rf /root/.indy_client/wallet/

/usr/share/libvcx/provision_agent_keys.py --wallet-name culedger --enterprise-seed ${VCX_INSTITUTION_DID_SEED} "https://eas01.pps.evernym.com" culedger-key > /opt/sovrin/vcxconfig.json

sed -i "s,\"genesis_path\": \"<CHANGE_ME>\",\"genesis_path\": \"/opt/sovrin/${VCX_GENESIS_PATH}\",g" ./vcxconfig.json
sed -i "s,\"institution_logo_url\": \"<CHANGE_ME>\",\"institution_logo_url\": \"${VCX_INSTITUTION_LOGO_URL}\",g" ./vcxconfig.json
sed -i "s,\"institution_name\": \"<CHANGE_ME>\",\"institution_name\": \"${VCX_INSTITUTION_NAME}\",g" ./vcxconfig.json
