// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */

var userEmail = "";
function addRandomGreeting() {
    const greetings =
        ['I really adore Minions! They are soooo cute!',
            'I\'ve always been the shortest in the class. Please tell me if you have a secret method to grow taller.',
            'I watch a lot of American TV series. My favorite is \'Person of Interest \'.',
            'I like Taylor Swift, 1989 is the best.'];

    // Pick a random greeting.
    const greeting = greetings[Math.floor(Math.random() * greetings.length)];

    // Add it to the page.
    const greetingContainer = document.getElementById('greeting-container');
    greetingContainer.innerHTML = greeting;
}

async function onLoad() {
    await login();
    await getComments();
}  

async function login() {
    const response = await fetch('/login');
    const responseData = await response.json();
    if (responseData["email"] === "") {
        var loginUrl = responseData["url"]
        document.getElementById('login-container').innerHTML = " <a href="+ loginUrl +"> Login </a>";
    } else {
        userEmail = responseData["email"];
        var logoutUrl = responseData["url"];
        document.getElementById('login-container').innerHTML = "<span id='welcome-message'>Hello, "+ userEmail + "</span><a href="+ logoutUrl +"> Logout </a>";
    }
}

async function getComments() {
    const response = await fetch('/data');
    const responseText = await response.json();
    var comment = document.getElementById('comment-container')
    responseText.forEach(r => {
        comment.appendChild(createListElement(r.comment, r.user, r.email));
    })

    if (userEmail === "") {
        document.getElementById('post-comment-container').style.display="none";
    }else {
        document.getElementById('post-comment-container').style.display="inline-block";
        document.getElementById('email-address').setAttribute("value",userEmail)
    }

}

function createListElement(text, user, email) {
    const liElement = document.createElement('li');
    liElement.innerHTML = "<span class='comment-entry-user'>" + user +" ("+ email+ ")</span>: " + text;
    liElement.setAttribute("class", "comment-entry")
    return liElement;
}