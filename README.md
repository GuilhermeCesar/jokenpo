<h2>Setup</h2>
<li>Utilizar java 15</li>
<li>Tenha o docker previamente instalado na sua máquina</li>
<li>Você deve ter o mavem previamente instalado na sua máquina</li>

<li> <a href="docker/docker-compose.yml">  Senhas dos bancos estão no docker compose</a></li>
<li>Windows sem o hyperv não irão funcionar em localhost, utilizar o IP da VM no arquivo <a href="assembleia-api/src/main/resources/application.yml">application para o banco </a></li>
<li>Para rodar o banco execute o seguinte comando na raiz do projeto</li>
<code>
cd docker && docker-compose up
</code>

<li>abrir no draw.io 
    <a href="https://drive.google.com/file/d/1jfTjkMJNupU8g1c8CXjPSovbsK8J6U2g/view?usp=sharing">
        <label>       
            Modelo ER    
        </label>
    </a>
</li>
<li>Na Raiz do projeto <code>mvn clean install</code></li>

<h2>Aplicação</h2>
<li>Rodar o spring na sua IDE, apenas a mensageria e o banco estão no docker</li>
<li>Utilizar branch master</li>
<li>
    <a>http://localhost:8080/api/jokenpo/swagger-ui/index.html</a>
</li>
<li>Utilizar o profile default</li>
<li>
O Batch roda a cada 1 minutos, caso necessário altere em 
<a href="batch/src/main/resources/application.yml"> application.yml</a> 
</li>

<h3 align="left">Languages and Tools:</h3>
<p> 
    <a href="https://www.java.com" target="_blank"> 
        <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.linux.org/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/> </a> <a href="https://www.mysql.com/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a>
</p>



