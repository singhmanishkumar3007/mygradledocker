# mygradledocker
Demo spring boot using gradle , docker and kubernetes ( minikube )

 For Mac-
 Links followed -
 https://www.oreilly.com/ideas/how-to-manage-docker-containers-in-kubernetes-with-java
 
 http://www.baeldung.com/spring-boot-minikube
 
 
 Steps -
 
 1. Install Docker on Mac using brew install docker
 2. Type Command + spac , "Serach for "Docker" and follow steps to start docker engine
 3. Create Dockerfile as- 
 
 FROM frolvlad/alpine-oraclejdk8:slim
# ENV MONGO_URL mongodb://localhost/belfastjug_sample_01
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/mygradledocker-0.0.1-SNAPSHOT.jar /app/mygradledocker-01.jar
ENTRYPOINT ["java", "-jar", "/app/mygradledocker-01.jar"]

now build image as
docker build -t mygradledocker-01 .

if you want to run image and map it to external ip-
docker run --name=mygradledocker --publish 9003:8080 mygradledocker-01:latest
(we have mapped 9003 port to 8080 of application in docker image)


No push docker image to docker hub -
docker push smanish3007/ mygradledocker-01:latest

you can check docker images using -
docker images

you can see docker images running status-
docker ps

Kill docker container using-
docker rm -f "containername"


Now, image is pushed to docker hub , we will pull this image from kubernetes.

Steps-

4. Install hypervisor/Virtal box -> kubectl -> minikube - https://kubernetes.io/docs/tasks/tools/install-minikube/
5. After completing the installation, we can start Minikube, set VirtualBox as Hypervisor, and configure kubectl to talk to the cluster called minikube:

$> minikube start
$> kubectl config use-context minikube

Commands to get all contexts on mimikube -
kubectl config get-contexts

command to seitch to any particular context-
kubectl config use-context "contextname"

6. Minikube is single node based cluster available for development on local machine/VM

7. kubectl cluster-info
The output should look like this:

Kubernetes master is running at https://192.168.99.100:8443
To further debug and diagnose cluster problems, use 'kubectl cluster-info dump'.
At this stage, we’ll keep the IP in the response close (192.168.99.100 in our case). We’ll later refer to that as NodeIP, which is needed to call resources from outside of the cluster, e. g. from our browser.

Finally, we can inspect the state of our cluster:


$> minikube dashboard
This command opens a site in our default browser, which provides an extensive overview about the state of our cluster.

8. Now follow https://www.oreilly.com/ideas/how-to-manage-docker-containers-in-kubernetes-with-java

9. Step 8 says that add a service.yml file to configure service and replicationController configurations for kubernetes.

10. I have placed this config file as "service.yaml" under deployment/test folder of project's root.

11. Now navigate to deployment/test folder using cd command

12. use - kubectl apply -f service.yaml

    It will create service and othe replicvation controller-
    
    service "mygradledocker" created
replicationcontroller "mygradledocker" created

13. use- kubectl get svc to get list of services available

14 use- kubectl get pods to get list of pods with status

14. use-  minikube service mygradledocker to get service on default browser ( equivalent to us calling our api of micro service)

15. instead of step 14 - hit url - http://192.168.99.100:31040/{your rest endpoint} to get api up and running

16. inorder to kill pod- use - kubectl delete pod "podname"

17. inorder to get logs of a pod - use- 
e.g. https://kubernetes-v1-4.github.io/docs/user-guide/kubectl/kubectl_logs/
# Return snapshot logs from pod nginx with only one container
kubectl logs nginx

# Return snapshot of previous terminated ruby container logs from pod web-1
kubectl logs -p -c ruby web-1

# Begin streaming the logs of the ruby container in pod web-1
kubectl logs -f -c ruby web-1

# Display only the most recent 20 lines of output in pod nginx
kubectl logs --tail=20 nginx

# Show all logs from pod nginx written in the last hour
kubectl logs --since=1h nginx

18. Stop minikube using minikube stop


19. Cleaning up Service and Deployment
Afterward, we can remove Service and Deployment:

1
2
$> kubectl delete service "service-name"
$> kubectl delete deployment "deployment-name"


 
