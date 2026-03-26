fetch('/teamA/categoryData')
    .then(res => res.json())
    .then(data => {
        const labels = Object.keys(data);
        const values = Object.values(data);
        new Chart(document.getElementById('pieChart'), {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    data: values,
                    backgroundColor: [
                        '#ff6384', '#36a2eb', '#ffce56',
                        '#4bc0c0', '#9966ff', '#ff9f40'
                    ],
                    borderWidth: 0,
                    position: 'right',
                }]
            },
            options: {

                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'left',
                        labels: {
                            usePointStyle: true,
                            font: {
                                size: 15
                            },
                            pointStyle: 'circle',
                            padding: 17,
                            textAlign: 'left'
                        }
                        }
                },
            }
        });
    });