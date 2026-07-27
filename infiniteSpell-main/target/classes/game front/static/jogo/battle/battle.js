let damangeP1 =100
let lifeP1 = 100

function damangeP1Aplicator(){
    const porcent = (lifeP1 -damangeP1)
     const lifeBar = document.querySelector('.life')
     lifeBar.style.width = porcent + "%";
}

damangeP1Aplicator()