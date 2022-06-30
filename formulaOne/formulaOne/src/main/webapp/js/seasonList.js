/**
 * view-controller for seasonshelf.html
 * @author Marcel Suter
 */
document.addEventListener("DOMContentLoaded", () => {
    readSeasons();
});

/**
 * reads all seasons
 */
function readSeasons() {
    fetch("./formula/season/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showSeasonlist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the seasonlist as a table
 * @param data  the seasons
 */
function showSeasonlist(data) {
    let tBody = document.getElementById("seasonlist");
    data.forEach(season => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = season.title;
        row.insertCell(-1).innerHTML = season.author;
        row.insertCell(-1).innerHTML = season.publisher.publisherName;
        row.insertCell(-1).innerHTML = season.price;
        row.insertCell(-1).innerHTML = season.isbn;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editSeason";
        button.setAttribute("data-seasonuuid", season.seasonUUID);
        button.addEventListener("click", editSeason);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteSeason";
        button.setAttribute("data-seasonuuid", season.seasonUUID);
        button.addEventListener("click", deleteSeason);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editSeason(event) {
    const button = event.target;
    const seasonUUID = button.getAttribute("data-seasonuuid");
    window.location.href = "./seasonedit.html?uuid=" + seasonUUID;
}

/**
 * deletes a season
 * @param event  the click-event
 */
function deleteSeason(event) {
    const button = event.target;
    const seasonUUID = button.getAttribute("data-seasonuuid");

    fetch("./resource/season/delete?uuid=" + seasonUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./seasonshelf.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}