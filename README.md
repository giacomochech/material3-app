# **La nostra applicazione: DiarioM3**

Per mostrare le caratteristiche principali di Material Design 3 è stata importante la scelta dell’applicazione.

Abbiamo cercato di esprimere i 3 principi cardine del Material You(Comfortable,Iconoclastic,Spirited) in un'applicazione per l'autogestione personale. ***DiarioM3*** è infatti un “diario alimentare”, che aiuta l’utente a memorizzare le calorie assunte nel corso della giornata.

La scelta di un “diario alimentare” ha reso più immediata e intuitiva la comunicazione del primo principio: comfortable.

Il termine *comfortable* è stato introdotto da Christian Robertson nel video di presentazione come:  *How can a design system can make a person feel home with his device.*

Per rappresentare questo abbiamo voluto che la nostra applicazione potesse essere intuitiva e semplice, unendo il design ad un'esperienza di utilizzo facile ed immediata. 

Questo ha lo scopo di rendere ***DiarioM3*** uno strumento che possa essere usato quotidianamente.

Abbiamo, infatti,sviluppato un'interfaccia priva di menu incapsulati e nascosti.

Come vedremo tutte le informazioni e i pulsanti sono ben visibili e intuitivi.

L’aspetto Iconoclastic, definito come, *How can software exhibit the awareness to adapt and thrive through changes, blurring boundaries between sys apps and installed apps,*è stato invece trasmesso grazie alla combinazione di colori dinamici ed prendendo ispirazione da applicazioni di sistema

Il principio “Spirited” è, infine,  *imbue digital development with the spirit of the natural world, sense of aliveness through shapes and motions*. Questo è stato raggiunto grazie ad elementi di layout come le *Material Cards* oppure i diversi stili di bottoni, che rendono le superfici più naturali, emulando materiali del mondo reale come ad esempio la schermata principale, che cerca di rappresentare un calendario stampato senza elementi in rilievo.

**Struttura**

***DiarioM3*** è composta da 3 da tre pagine:*diario, cal. calorie* e *profilo*.

Nella pagina “**Diario”**, la pagina principale, il layout rappresenta un calendario  con la data e la somma delle calorie assunte in quella giornata.
62

Al di sotto si trova la lista degli alimenti mangiati, con le relative calorie associate. Da questa schermata si possono aggiungere alimenti alle liste selezionando il pulsante in rilievo contrassegnato con l'icona “+”. Scorrendo a destra o a sinistra si possono visualizzare i giorni futuri e passati con i relativi alimenti.

La seconda schermata, “***Calc. Calorie***” è utile per segnare l'obiettivo calorico giornaliero. È inoltre presente una calcolatrice del fabbisogno calorico che utilizza un’equazione indicata dall'OMS per ottenere il *Metabolic Equivalent of Task* a riposo.
L'ultima pagina, “Profilo”, racchiude i dati principali dell'utente che sono: l'obiettivo calorico, il nome e la mail. 

![Immagine che contiene testo, elettronico, screenshot

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.001.png)![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.002.png)![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.003.png)

*DiarioM3 schermate principali*


**Funzionalità e Design**

In questa sezione andremo ad esporre le principali funzionalità della nostra applicazione

1. **Aggiunta degli alimenti**Nella schermata “*Diario*” è presente un tasto in rilievo che permette di aggiungere un alimento.

   Questo, di default, viene aggiunto il giorno stesso,ma grazie al tasto “*Data*” c’è la possibilità di aggiornare questo valore con quello desiderato.

   Tramite il tasto aggiungi si conclude l'inserimento.

   Questo *DialogFragment* ha presenti nel suo layout molti componenti descritti dal material design 3:

- *MaterialCardView* in particolare di tipo *Outlined card*
- Tre *Button* di tipo *Filled button* 
- Due input di testo di tipo *Outlined text field*
- Un *MaterialDatePicker* 

![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.004.png)![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.005.png)![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.006.png)



*DiarioM3 schermata di aggiunta alimento*

**2.Eliminazione degli alimenti e panoramica della schermata Diario**

La schermata principale è formata da un *ViewPager* che permette di scorrere all'infinito a destra ed a sinistra per visualizzare i giorni passati e futuri, come un calendario classico.

Grazie a questa pagina abbiamo la possibilità di visualizzare la lista e le calorie assunte durante la giornata.

Questo contatore ha inoltre la possibilità di segnalare quando l’obiettivo calorico, impostato dall’utente, viene superato segnando le calorie assunte con un colore “*Error”*.

Gli alimenti hanno la possibilità di essere eliminati premendo il tasto “cestino” riposto a destra di ogni tessera.

I componenti di material design 3 presenti in questo *Fragment* sono:

- *MaterialCardView* in particolare di tutti e tre i tipi:*Filled card, Outlined card* ed *Elevated Card*
- Un *FloatingActionButton*
- Un *Icon text button* per ogni alimento


![Immagine che contiene testo, elettronico, screenshot

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.007.png)![Immagine che contiene testo, elettronico, screenshot

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.008.png)![Immagine che contiene testo, screenshot, elettronico, martinetto

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.009.png)



*DiarioM3 schermata Diario*

**3.Schermata Cal. Calorie**

La schermata *Cal. Calorie* e formata da una calcolatrice, per stimare il fabbisogno energetico.Questa utilizza un’equazione fornita dall’*OMS* negli anni ‘80.

La seconda metà di questa schermata invece dà la possibilità di modificare ed aggiornare il proprio obiettivo calorico. Questo dato persistente viene aggiornato e condiviso con tutte le pagine dell'applicazione.

I componenti di material design 3 presenti in questo *Fragment* sono:

- *MaterialCardView* in di tipo *Filled card*
- Un *Button* di tipo *Elevated*
- Due *Button* di tipo *Filled* 

Un *Menu* per selezionare il genere

- Tre input di test di tipo *Outlined*

Un ulteriore elemento di material design 3 chiaramente presente all’interno di questa pagina è l’utilizzo preciso dei colori.
Grazie alle palette create dinamicamente da Android si possono differenziare i bottoni e le sezioni dei layout in maniera chiara all’utente finale.

Un chiaro caso di differenziazione di colori è presente nei 3 bottoni.

![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.010.png)

Il bottone “*Calcola*” ha lo stesso colore base dello sfondo, così da comunicare all’utente la sua minor importanza rispetto ai due bottoni sottostanti.

Successivamente il bottone “*Annulla”*, descritto con il colore “*colorTertiaryContainer*” suggerisce un'importanza di secondo livello, ma comunque rilevante per l'utilizzo di questa schermata 

Infine il più importante, il Bottone “*Accetta*”, spicca sui 3 grazie all'utilizzo del colore primario della palette.

*DiarioM3 schermata Cal. Calorie*

**4..Schermata Utente**

La schermata *Utente* è l’ultima pagina delle tre presente in questa applicazione.Ha il compito di mostrare all’utente le informazioni principali inserite dallo stesso.

Al suo interno è possibile modificare il nome e la mail accedendo ad un *DialogFragment* che appare cliccando sulle stesse.

I componenti di Material design 3 presenti in questa sezione sono:

- *MaterialCardView* in di tipo *Outlined*
- Due Button di tipo *Text Button*
- Due input di test di tipo *Outlined*

Ancora una volta, l’utilizzo di elementi di design come i *Text Button* rendono più intuitiva e semplice l’applicazione. Questi pulsanti, infatti, suggeriscono all’utente che le informazioni immesse non sono rilevanti per il corretto utilizzo dell’applicazione a differenza dei dialog e pulsanti precedentemente mostrati.

![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.011.png)![Immagine che contiene testo, screenshot, elettronico

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.012.png)

*DiarioM3 schermata Utente*




#


**Navigation bar**

Per muoversi tre le schermate ***DiarioM3*** utilizza una Navigation Bar.

Questo elemento ha ricevuto particolari attenzione nel passaggio da MaterialDesign 2 al 3.

Come spiegato nella documentazione del Material Design 3, questa deve seguire particolari direttive.

Abbiamo quindi implementato quelle che secondo noi sono le principali.

Questa barra accessibile in qualunque momento racchiude in modo efficace le informazioni di ogni schermata.

Quando viene selezionata la schermata un'animazione ,che si svolge in un solo asse, in questo caso nell’asse orizzontale, crea un'asola colorata intorno all’icona.

Quest'ultima passa da essere *Twotone* ad una *Filled* per rendere la selezione ancora più evidente.

Infine viene mostrato il titolo della schermata selezionata che fornisce una breve descrizione della pagina.

Volendo concentrarci al massimo sulla pulizia del design, abbiamo deciso di rendere il tasto gesture, sul fondo dello schermo, integrato al nostro design. Il motivo principale di questa scelta è dovuto allo spirito *Iconoclastic* dettato da *Christian Robertson*.

Per essere coerenti al massimo con questa filosofia abbiamo reso lo status bar *translucid.*

![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.013.png)![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.014.png)![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.015.png)

*DiarioM3 NavigationBar e gestureButton*

![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.016.png)

*DiarioM3 StatusBar*

**Elementi principali di Material Design 3 e Implementazione**

Come elencato prima, gli elementi del material che sono presenti all’interno dei layout della nostra applicazione sono principalmente:

1. Navigation bar
1. Button
1. Cards
1. Text fields

Le implementazioni di questi componenti sono avvenute in questo modo:

1. Navigation bar: questa è stata implementata all'interno della file *activity\_main.xml* nel seguente modo

![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.017.png)

in particolare la line app:labelVisibilityMode="selected" serve per far si che le label vengano nascoste quando non è selezionata la schermata.

app:itemRippleColor="@android:color/transparent" è utile per nascondere l’animazione ripple che non seguirebbe le indicazioni del Material design 3

Gli elementi della Navigation bar sono descritti nel file *activity\_menu\_drawer.xml* presente nella cartella *menu* delle risorse.

Questo è implementato con il seguente codice

![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.018.png)



1. ![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.019.png)I *Button* sono implementati in maniera classica, ma ad essi viene applicato lo stile a seconda del tipo di bottone.
   Questi sono quelli utilizzate nel nostro codice : *elevated Botton, filled Button, text button* e *text icon button.*
   Questo è un esempio base di come tutti i bottoni sono stati implementati*


   in particolare questa riga

   ` `style="@style/Widget.Material3.Button.ElevatedButton" 

   rende il bottone di tipo *elevated* 







1. ![](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.020.png)![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.021.png)Le Card in Materia Design 3 sono di 3 tipi, tutti presenti all’interno di “DiarioM3”, essi sono: *Elevated card, Filled card* ed *Outlined card*
   Queste Card sono state implementate nel seguente modo:

   nel quale style qualifica ancora una volta il tipo di Card.


1. I Text fields che sono presenti in “DiarioM3” sono tutti di tipo *Outlined text field* e li abbiamo implementati grazie al seguente codice

![Immagine che contiene testo

Descrizione generata automaticamente](./images/Aspose.Words.0e14c6c9-c761-4f03-b468-5fe76f013fea.022.png)


