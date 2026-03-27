Promise.all([
    fetch('/teamA/subscriptionUsage').then(res => res.json()),
    fetch('/teamA/subscriptionCost').then(res => res.json())
]).then(([usageData, costData]) => {
    const labels = Object.keys(usageData);

    new Chart(document.getElementById('barChart'), {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Pr. dag',
                    data: Object.values(usageData),
                    backgroundColor: 'rgb(72,184,184)',
                    barThickness: 20,
                    borderRadius: 4,
                },
                {
                    label: 'Total Pris',
                    data: Object.values(costData),
                    backgroundColor: 'rgb(61,123,255)',
                    barThickness: 20,
                    borderRadius: 4,
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {

                legend: { display: true }
            }
        }
    });
});