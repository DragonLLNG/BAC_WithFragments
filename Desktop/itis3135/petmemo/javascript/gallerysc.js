'use strict';

const api='live_BafSisQkq9qjRxoQQst6pGOcEJFl71JEsAQuYHqMrLOS2k7CQus3YkDEFFU1yPFb'

const base_url=`https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=beng&api_key=${api}`


//global variables
let url;

//Hamburger menu
const hamburger = document.querySelector(".hamburger");
const navMenu = document.querySelector(".nav-menu");

hamburger.addEventListener("click", mobileMenu);


function mobileMenu(e) {
    console.log(e.target);
    hamburger.classList.toggle("active");
    navMenu.classList.toggle("active");
}




//Toggle 
const newButton = document.querySelector(".new-btn");
console.log(newButton);
newButton.addEventListener("click", function(){
    document.querySelector(".new-section").classList.toggle("hide");
});



const imageContainers = document.querySelectorAll('.container-img-acc img');

imageContainers.forEach(imageContainer => { 
    imageContainer.classList.add('main-img-acc');
});

async function getImage(){
    
}



