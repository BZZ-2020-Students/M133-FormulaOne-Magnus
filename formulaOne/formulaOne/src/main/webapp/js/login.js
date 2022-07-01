

function login() {
    fetch("./formula/user/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {

        })
        .catch(function (error) {
            console.log(error);
        });
}