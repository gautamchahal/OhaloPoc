const APIURL = "http://localhost:8080/";

const main = document.getElementById("main");
const favMovies = document.getElementById("fav-movies");
const form = document.getElementById("form");
const search = document.getElementById("search");
let favourites = [];
let favids = [];


window.addEventListener("load", () => {
    let userId = localStorage.getItem('userId');
    if (userId == null) {
        let uuid = create_UUID();
        localStorage.setItem("userId", uuid);
        userId = uuid;
    }


});

function createPutFetch(url, sendData) {
	//console.log(sendData);
    const putMethod = {
        method: 'PUT', // Method itself
        headers: {
            'Content-type': 'application/json; charset=UTF-8;' // Indicates the content 
        },
        body: "["+sendData+"]"// We send data in JSON format
    };

    // make the HTTP put request using fetch api
    fetch(url, putMethod)
        .then(response => response.json())
        .then(data => console.log(data)) // Manipulate the data retrieved back, if we want to do something with it
        .catch(err => console.log(err));
}


async function getMoviesAllMovies(url) {
    const resp = await fetch(url);
    const respData = await resp.json();
    //console.log(respData);
    showMovies(respData);
}
async function getFavMovies(url) {
    const resp = await fetch(url);
    const respData = await resp.json();

   // console.log(respData);
	let ids = respData.map(a => a.episode);
	favourites=[...ids];
	let epid = respData.map(a => a.id);
	favids=[...epid];
    showFavMovies(favourites);
}

function showMovies(movies) {
    // clear main
    main.innerHTML = "";
    let titleElement = document.createElement("h3");
    titleElement.innerHTML = movies[0].title;
    main.appendChild(titleElement);
    movies.forEach((movie) => {
        const { id, season, episode } = movie;

        const movieEl = document.createElement("div");
        movieEl.classList.add("movie");

        movieEl.innerHTML = `
            <div class="overview" onclick="addFavouriteMovie('${movies[0].title}','${season}', '${episode}', '${id}')">
                <p>Episode:${episode}</p>                
            </div>
        `;

        main.appendChild(movieEl);
    });
}

function showFavMovies(movies) {
    // clear main
    favMovies.innerHTML = "";

    movies.forEach((movie) => {

        const movieEl = document.createElement("div");
        movieEl.classList.add("movie");

        movieEl.innerHTML = `
            <div class="overview">
                <p>Episode:${movie}</p>
            </div>
        `;

        favMovies.appendChild(movieEl);
    });
}


function addFavouriteMovie(title, season, movie, id) {

    favourites = [...favourites, +movie];
    favourites = favourites.filter(onlyUnique);
    favids = [...favids, +id];
    favids = favids.filter(onlyUnique);
    console.log(favids);
    showFavMovies(favourites);

    let userId = localStorage.getItem('userId');
    let putURI = APIURL + `user/select?id=${userId}&title=${title}&season=${season}`
    createPutFetch(putURI, favids);
    // setFavourites(newFavouriteList);
    // saveToLocalStorage(newFavouriteList);
};

form.addEventListener("submit", (e) => {
    e.preventDefault();
    let title = form.elements['search'].value;
    let season = form.elements['season'].value;
    let showURL = APIURL+`show?title=${title}&season=${season}`;
    let userId = localStorage.getItem('userId');
    getMoviesAllMovies(showURL);
    let favUrl = APIURL+`user/select?id=${userId}&title=${title}&season=${season}`;
    getFavMovies(favUrl);
    // const searchTerm = search.value;

    // if (searchTerm) {
    //     getMovies(SEARCHAPI + searchTerm);

    //     search.value = "";
    // }
});

function onlyUnique(value, index, self) {
    return self.indexOf(value) == index;
}

function create_UUID() {
    var dt = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (dt + Math.random() * 16) % 16 | 0;
        dt = Math.floor(dt / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}