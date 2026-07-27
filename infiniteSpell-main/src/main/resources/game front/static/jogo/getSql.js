let ACI = 0; 
let cards = []; 
let deck=[];

async function loadDeck() {
    console.log("loading deck mother fuker");

    const json = window.returnJava.getCards();
    cards = JSON.parse(json);

    update();
}

function update() {
    const shelf1 = document.querySelector('#s1');
    const shelf2 = document.querySelector('#s2');

    if (shelf1) shelf1.innerHTML = '';
    if (shelf2) shelf2.innerHTML = '';

    let cardsIndex = cards.slice(ACI, ACI + 10);

    for (const card of cardsIndex) {
        let type = "null";
        const raridadeNum = card.raridade;

        if (raridadeNum == 1) type = "Terra";
        else if (raridadeNum == 2) type = "Água";
        else if (raridadeNum == 3) type = "Fogo";
        else if (raridadeNum == 4) type = "Ar";
        else type = "Éter";
         

        const newCard = document.createElement('div');
        newCard.className = 'cards';
        newCard.innerHTML = `
            <img src="" class="imageCard">
            <span>${card.nome}</span>
            <small style="color: #fb6767; font-size: 10px;">Dano: ${card.dano} | Tempo: ${card.tempo_conjuracao}s | Tipo:${type}</small>
        `;

        const img = newCard.querySelector('.imageCard');
        if (img) img.src = card.url;

        newCard.addEventListener('click', () => {
            const slots = document.querySelectorAll('.cardSlot');
            for (let slot of slots) {
                if (slot.children.length === 0) {

            let cardInfo= {
                nome: card.nome,
                dano: card.dano,
                tempo_conjuracao: card.tempo_conjuracao,
                tipo: type 
            };

            deck.push(cardInfo);

                slot.appendChild(newCard);
                break;
                }
            }
        });

        if (shelf1 && shelf1.childElementCount < 5) {
            shelf1.appendChild(newCard);
        } else if (shelf2) {
            shelf2.appendChild(newCard);
        }
    }
}
window.onload = function() {
    loadDeck();

    const btnA = document.getElementById("btnA");
    
    const btnP = document.getElementById("btnP");

    if (btnP) {
        btnP.addEventListener("click", function(event) {
            if (ACI + 10 < cards.length) {
                ACI += 10;
                update();
            }
        }, false);
    } 
    
    if (btnA) {
        btnA.addEventListener("click", function(event) {
            if (ACI >= 10) {
                ACI -= 10;
                update(); 
            }
        }, false);
    }

    // Chama a desgrassa do listner
    addListner();
};


  function addListner() {
    const btn = document.getElementById("btnAP");
    
  
    btn.addEventListener("click", async function(event) {
         callAPI();
   
        const screen1 = document.querySelector('#screen');
        if (screen1) screen1.innerHTML = ''; 
    
        const screen = document.querySelector('#tela');
        if (screen) {
            screen.innerHTML = ` 
                <div class="loding">
                    <h1 class="text">loading...</h1>
                    <div class="conteiner-penta">
                        <h1 class="penta">⛤</h1>
                    </div> 
                </div>
            `;
        }
        


        
    }, false);

      const btn2 = document.getElementById("btnB");
    btn2.addEventListener("click", async function(event) {
         window.location.href ="battle/battle.html"
  
   
    }, false);
}


function callAPI(){
     try {
             window.criatec.criate();

        const checkInterval = setInterval(() => {
            let finished = false;
            
            try {
                finished = window.criatec.returnData();
            } catch (err) {
               
            }

            if (finished) {
                clearInterval(checkInterval); 
                
        
                const screen = document.querySelector('#tela');
                if (screen) screen.innerHTML = ''; 
                
                const screen1 = document.querySelector('#screen');
                if (screen1) screen1.innerHTML = ''; 

                loadDeck();
                
                OpeningAnimation();
            }
        }, 500);
        } catch {
        
         }

/*   const r = new Promise((resolve) => {
       let result = 
       resolve(result);
   });
    
   async function f2() {
       let r2 = await r;
     
       if(r2){
           const screen = document.querySelector('.screen');
           if (screen) screen.innerHTML = ''; 
           console.log("apagou tudo todo mundo morreu :)");
       }
       

      
   }
   
   f2();
}*/

}

function OpeningAnimation() {
    const container = document.querySelector('#tela') || document.body
    const openingCard = document.createElement('div');
    openingCard.className = 'card-opening-anim';
    openingCard.innerHTML = `
    
    <div class="spectrun2"> </div>
  <div class="cards2">
    <img src="infiniteSpell-main\src\main\resources\game front\img\logo.png" class="imageCard">
      <span>infiniteSpell</span>
    <small style="color: #fb6767; font-size: 10px;">Dano:9999 | Tempo:0s | Tipo:test</small>
       
  </div>

            
        `;


    container.appendChild(openingCard);

    setTimeout(() => {
        openingCard.remove();
          window.location.reload(true)
    }, 7000);
}

function returnDeck(IdCardR){
    return deck[IdCardR] || null;
}