fetch('/teamA/allUsersSubscriptionsCount')
    .then(res => res.json())
    .then(data => {
        const labels = Object.keys(data);
        const values = Object.values(data);
        new Chart(document.getElementById('polarChart'), {
            type: 'polarArea',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: 'Abonnementer',
                        data: Object.values(values),

                        backgroundColor: [   '#ff6384', '#36a2eb', '#ffce56',
                            '#4bc0c0', '#9966ff', '#ff9f40'
    ],
                        barThickness: 20,
                        borderRadius: 4,
                        borderWidth: 0,
                    },
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    r: {
                        ticks: {
                            display: false
                        },
                        grid: {
                            color: 'rgba(255, 255, 255, 0.5)'
                        }
                    }
                },
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
                }
            }
        });
    });