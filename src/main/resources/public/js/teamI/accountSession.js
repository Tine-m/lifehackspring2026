


document.getElementById('').addEventListener('submit', function checkStatus() {
        fetch("/login")
            .then(res => res.json())
            .then(data => {

                if (data === "true") {
                    console.log("DET VIRKERE");
                } else {
                    console.log("FUck du fejlede. Magt dit liv")
                }

            });
    }
)

