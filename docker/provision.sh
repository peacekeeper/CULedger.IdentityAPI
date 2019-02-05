#!/bin/sh

rm /opt/sovrin/vcxconfig.json
rm -rf /root/.indy_client/wallet/

/usr/share/libvcx/provision_agent_keys.py --wallet-name culedger --enterprise-seed ${VCX_INSTITUTION_DID_SEED} "https://eas01.pps.evernym.com" culedger-key > /opt/sovrin/vcxconfig.json
