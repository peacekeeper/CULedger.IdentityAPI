On an linux based host (ubuntu) ensure the following is installed
docker -v to get the version if no version shows up install docker from the instructions below. 

https://docs.docker.com/install/linux/docker-ce/ubuntu/ 

Install using the repository
Before you install Docker CE for the first time on a new host machine, you need to set up the Docker repository. Afterward, you can install and update Docker from the repository.

SET UP THE REPOSITORY
Update the apt package index:

$ sudo apt-get update
Install packages to allow apt to use a repository over HTTPS:

$ sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
Add Docker’s official GPG key:

$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
Verify that you now have the key with the fingerprint 9DC8 5822 9FC7 DD38 854A E2D8 8D81 803C 0EBF CD88, by searching for the last 8 characters of the fingerprint.

$ sudo apt-key fingerprint 0EBFCD88
    
pub   rsa4096 2017-02-22 [SCEA]
      9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88
uid           [ unknown] Docker Release (CE deb) <docker@docker.com>
sub   rsa4096 2017-02-22 [S]
Use the following command to set up the stable repository. To add the nightly or test repository, add the word nightly or test (or both) after the word stable in the commands below. Learn about nightly and test channels.
$ sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

INSTALL DOCKER CE
Update the apt package index.

$ sudo apt-get update
Install the latest version of Docker CE and containerd, or go to the next step to install a specific version:

$ sudo apt-get install docker-ce docker-ce-cli containerd.io

Once you have docker installed

BUILD: 

docker build -f ./docker/Dockerfile . -t culedger-identityapi --build-arg VCX_INSTITUTION_LOGO_URL="https://culedger.s3.amazonaws.com/Connect.Me/CULedger-ConnectMe-Logo-256x256.png" --build-arg VCX_INSTITUTION_NAME="CULedger" --build-arg VCX_CREDENTIAL_NAME="MyCUID" 

RUN:

docker run -ti -p 8080:8080 -v culedger-identityapi-state:/opt/culedger-identityapi-state culedger-identityapi:latest

Give the IP and Port 8080 to those that need to interact with the api.