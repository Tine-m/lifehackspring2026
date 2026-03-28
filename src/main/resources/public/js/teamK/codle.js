
document.body.addEventListener("click", (e) => {
    if (e.target.classList.contains("btn-home")){
        window.location.href = "/codle"
    }

    if (e.target.id === "btn-start"){
        window.location.href = "/codle/play"
    }

    if (e.target.id === "btn-lifehack"){
        window.location.href = "/"
    }

    if (e.target.classList.contains("btn-continue")){
        let popUps = document.querySelectorAll(".pop-up.active");
        popUps.forEach(popUp => {
            popUp.classList.remove("active");
            popUp.classList.add("hidden");
        });
        window.location.href = "/codle/play";
    }
})