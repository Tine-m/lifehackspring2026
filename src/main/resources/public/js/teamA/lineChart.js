Promise.all([
    fetch('/teamA/totalPricePerCategory').then(res => res.json())
]).then(([usageData]) => {
    const labels = Object.keys(usageData);
    const values = Object.values(usageData);

    new Chart(document.getElementById('lineChart'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Kategori',
                    data: values,
                    dataType: 'DKK',
                    backgroundColor: 'rgb(72,184,184)',
                    borderColor: 'rgb(61,123,255)',
                    barThickness: 20,
                    borderRadius: 4,
                },
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: false }
            }
        }
    });
});