# Coding challenge

## Note sul progetto ed Esecuzione

Il progetto è un progetto Maven e può essere eseguito nei modi classici in cui un progetto Maven può essere eseguito.

Per la realizzazione del progetto è stata utilizzata la versione 17 di Java (ultima LTS [https://www.oracle.com/java/technologies/java-se-support-roadmap.html](https://www.oracle.com/java/technologies/java-se-support-roadmap.html)) 
e per dare la possibilità anche a chi non la avesse installata è stata creata 
una facility per l'esecuzione, ovvero è stato reso possibile creare un'immagine docker.

Per creare l'immagine eseguire il seguente comando (dall'interno della radice del progetto)
```shell
docker build -t demo-fabrick:latest .
```

Il precedente comando crea un'immagine docker chiamata **demo-fabrick**. Per avviare l'immagine eseguire il comando

```shell
docker run -d -p8080:8080 demo-fabrick:latest
```

se eseguito correttamente l'output è l'id del container appena creato.

All'interno del progetto si trova anche una collection di postman contenente 6 request: le request che iniziano per 
SANDBOX sono richieste fatte direttamente alla piattaforma di sandbox e sono state utilizzate per verificare il comportamento
e per controllare se la risposta coincide con le API sviluppate all'interno del progetto e che, nella collection postman,
sono quelle che iniziano per DEMO.

### API

Base Url = `/demo-api/account/{accountId}`

1. GET `/balance` 
2. GET `/transactions` 
3. POST `/money-transfer`

### Indirizzi utili

[Swagger (http://localhost:8080/swagger-ui/index.html#/)](http://localhost:8080/swagger-ui/index.html#/) contenente la descrizione dei servizi disponibili, oggetti, e valori restituiti

[Console H2](http://localhost:8080/h2-console/login.jsp) per la visualizzazione dei dati all'interno del DB (si resetta ad ogni riavvio dell'applicazione)

## Struttura del progetto

Il package di base è **com.example.demo**, dentro al quale abbiamo i seguenti package:

 - client: contiene tutte le classi necessarie a gestire la comunicazione con le API esterne
 - configuration: contiene le classi utilizzate per la configurazione del progetto
 - controller: contiene i rest controller
 - dto: contiene le classi DTO restituite dai controller
 - enumeration: contiene gli enum
 - exception: contiene la definizione di eccezioni custom utilizzate all'interno del progetto
 - mapper: contiene le interfacce che saranno poi autoimplementate da mapstruct per la conversione di classi
 - model: contiene i modelli di dominio
 - repository: contiene le interfacce per la comunicazione con il layer DB
 - service: contiene i service dell'applicazione responsabili della business logic
 - validation: contiene la definizione delle classi necessarie al meccanismo di validazione e contiene 
un sotto-package contenente le regole di validazione dell'oggetto che descrive un'operazione di bonifico.

## Comunicazione con le API esterne

Come detto tutte le classi necessarie alla comunicazione con le API esterne di trovano dentro al package **com.example.demo.client**.

Per gestire la risposta del server è stata creata la classe generica **ServerResponse<T>**

```java
public class ServerResponse<T> {

	private EServerResponseStatus status;
	private List<ServerErrorResponse> errors;
	private T payload;

	...
}
```

dove **ServerErrorResponse** è l'errore restituito

```java
public record ServerErrorResponse(String code, String description, String params) {}
```

mentre il payload è del tipo generico `T` e dipende dall'API chiamata.