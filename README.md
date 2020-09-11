# Brasileirão

<a href="files/brasileirao.apk" download="brasileirao.apk">Baixe apk aqui</a>

<a href="files/Brasileirao.postman_collection.json" download="Brasileirao.postman_collection.json">Baixe postman collection aqui</a>

## Índice
- [Tecnologias](#tecnologias-usadas)
- [Arquitetura](#Arquitetura-e-Definições)
- [Features](#Features)
- [CI-CD](#CI-CD)
- [Ideais Descartadas](#Ideias-Descartadas)
- [Coisas para melhorar](#coisas-para-melhorar)

## Tecnologias usadas
* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Google Analytics](https://firebase.google.com/docs/analytics?hl=pt-br)
* [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging?hl=pt-br)
* [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics?hl=pt-br)
* [Firebase App Distribution](https://www.bitrise.io/)
* [Bitrise](https://firebase.google.com/docs/app-distribution)

## Arquitetura e Definições

Este projeto segue os princípios do "Clean Architecture". Aqui como o projeto implementa ele:

### Layers Architecture
Decidi separar cada layer em um modulo no projeto, sendo App a camada de **Presentation**, **Data** e **Domain**,
isso ajuda a reforçar a arquitetura, não sendo possivel acessar por exemplo a cada de Data dentro de domain.

Uma outra forma que eu gosto seria cada feature ser uma lib e cada lib implementar clean architecture dentro dela,
mas para simplificar o desenvolvimento resolvi não fazer dessa forma.

#### App/Presentation Layer
O modulo **app** é minha camada de **presentation**, elas estão juntas para simplificar, mas gosto da
ideia de separar essas 2 camadas, deixando o app como definições de libs, di entre outras coisas.

Essa camada tem a responsabilidade de lidar com a interface do usuario, também utilizo a pattern **MVVM** nessa camada.

#### Domain Layer
A camada de dominio simplesmente é a mais abstrata de todas, contendo **UseCases**, modelos e interfaces de repositórios.

Os UseCases são criados com um retorno de uma **disjoint function** **Either**(no meu caso Response), o que significa que é designado para conter
ou a parte esquerda(no meu caso Failure) ou direita(no meu caso Success).

#### Data Layer
A camada de dados é o ponto de acesso para camadas de dados externas e é usada para buscar dados de várias fontes,
no meu caso Remote e Banco de dados Local.

Ela também contem as implementações dos 'repositories' da camada de domain como 'mappers' das classes das camadas. 

## Features
O aplicativo tem uma tela para mostrar jogos do Brasileirão Serie A e outra tela com detalhes desses jogos com Lance-a-Lance deles.

* **Tela de Jogos** - Simples lista de tabela de jogos, podendo funcionar offline (se já tiver pego os dados remotos)

![Imgur](https://i.imgur.com/GTFewcv.jpg)
    
* **Tela detalhe do Jogo + Lance-a-Lance** - Tela com mais detalhamento do jogo e uma área com alguns lances importantes do jogo.

![Imgur](https://i.imgur.com/dtaiA0Q.jpg)

Lance-a-Lance com push notification

[[Video mostrando o "real-time" do Lance-a-Lance]](https://www.youtube.com/watch?v=D9QnUtZVtaA)

## CI CD
Utilizei Bitrise e Firebase App Distribution.

Como não tenho acesso de admin no git, não consegui colocar web hooks no Bitrise, então ainda não consigo colocar para
dar push e ativar o Pipeline no Bitrise.

Ativei o App Distribution no Firebase, então assim que eu der uma build no Pipeline do Bitrise, um dos ultimos
scripts está configurado com o firebase e irá mandar o app para lá.

(Posso configurar o Webhooks se o admin do git puder me passar o que eu preciso para ativar)

Link para tester no Firebase App Distribution
https://appdistribution.firebase.dev/i/261efaaafbe1d043

## Ideias descartadas
* **Clean Architecture com libs** - Minha ideia inicial era features e cada feature sendo uma lib,
cada lib implementando Clean architecture e o App principal juntando essas libs, mas decidi não fazer dessa maneira
para facilitar o projeto e também não fazia sentido pelo tamanho do projeto.
* **Kotlin DSl Gradle** - Com o suporte do Kotlin DSL na ultima versão do Android Studio melhorada, pensei em
criar o gradle com kotlin, mas isso poderia mais me atrasar do que ajudar.
* **Tabela de pontuação** - Muito trabalho fora do escopo.
* **Utilizar Flow** - Decidi não utilizar Coroutines Flow por conta da minha decisão de ter o domain um modulo puro kotlin

## Coisas para melhorar
* **Local/Offline First** - Por ter requisito de ter acesso offline, decidi utilizar um banco de dados local
(**room**), por ter tempo que não faço um aplicativo offline acabei tomando algumas decições que poderiam ser melhores:
    * Utilizar 2 ou 3 **UseCases** aonde poderiam ser 1 só no **ViewModel** 
    * Utilizar o Flow para ter um banco Local reativo, mas com a minha decisão de ter o domain como um modulo puro kotlin 
    isso dificultou a utilização do Flow com esse contexto(ajudaria no primeiro caso)
* **Webhooks** - Como não sou admin do projeto eu não pude adicionar webhooks, assim não podendo configurar 
para a cada **push/pullRequest** em uma branch específica acionar uma build no **Bitrise**.
* **Paginação** - Paginação para o Lance a Lance
* **UseCases** - UseCases sem ser de Remoto/DB
* **Testes** - Maior variedade e quantidade de testes
* **UI/UX** 
    * Criar tabelas de jogos com as rodadas
    * Lance-a-Lance somente em jogos que já aconteceram e em jogos que estão acontecendo
    * Varios tipos de item na lista de Lance-a-Lance
    * Placeholders em áreas que a lista está vazia
     
