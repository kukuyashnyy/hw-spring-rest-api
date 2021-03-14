<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <style>
        label {
            display: inline-block;
            width: 150px;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>REST API</h1>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody></tbody>
    <tfoot>
    <tr>
        <td></td>
        <td><input id="title" type="text" placeholder="Title"/></td>
    </tr>
    <tr>
        <td></td>
        <td style="text-align: right">
            <button id="add">Add</button>
        </td>
    </tr>
    </tfoot>
</table>

<script>
    function displayTasks(tasks) {
        console.log(tasks);
        const tbody = document.querySelector('tbody');
        tbody.innerHTML = '';
        tasks.forEach(task => {
            let tr = document.createElement('tr');
            for (const prop in task) {
                let td = document.createElement('td');
                td.append(document.createTextNode(task[prop]));
                tr.append(td);
            }
            tbody.append(tr);
        });
    }

    const baseUrl = "http://localhost:8080/api/v1/todolist";
    //document.querySelector("#load").addEventListener('click', event => {
    fetch(baseUrl)
        .then(response => response.json())
        .then(json => {
            displayTasks(json);
        });

    //})

    function addTask(task) {
        let tr = document.createElement('tr');
        for (const prop in task) {
            let td = document.createElement('td');
            td.append(document.createTextNode(task[prop]));
            tr.append(td);
        }
        document.querySelector('tbody').append(tr);
    }

    document.querySelector("#add")
        .addEventListener('click', event => {
            let titleInput = document.querySelector('#title');
            const task = {
                title: titleInput.value,
            };
            fetch(baseUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(task)
            }).then(response => {
                titleInput.value = '';
                return response.json();
            }).then(addTask);
        });

</script>

</body>
</html>
