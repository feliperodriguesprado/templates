function Index() {

	var jaxbList;

	function initialize() {

		setEvents();

		getJaxbList(function() {

			if (jaxbList.length > 0) {
				getJaxbByIdQuery(jaxbList[Math.floor(Math.random()
						* jaxbList.length)].id);
			}

		});
	}

	function setEvents() {

		$("#inputPUT").val("");

		$("#btnPUT").on("click", function() {

			if ($("#inputPUT").val()) {
				putJaxb($("#inputPUT").val());
				$("#inputPUT").val("");
			} else {
				alert("Informe corretamente o nome");
			}

		});

		$("#inputPOSTId").val("");
		$("#inputPOSTDescription").val("");

		$("#btnPOST").on(
				"click",
				function() {

					if ($("#inputPOSTId").val() > 0
							&& $("#inputPOSTDescription").val()) {

						postJaxb($("#inputPOSTId").val(), $(
								"#inputPOSTDescription").val());

						$("#inputPOSTId").val("");
						$("#inputPOSTDescription").val("");
					} else {
						alert("Informe corretamente o ID e/ou nome");
					}
				});

		$("#inputDELETE").val("");

		$("#btnDELETE").on("click", function() {

			if ($("#inputDELETE").val() > 0) {
				deleteJaxb($("#inputDELETE").val());
				$("#inputDELETE").val("");
			} else {
				alert("Informe corretamente o ID");
			}

		});

	}

	function getJaxbList(callback) {

		$('#table1 tbody').html("");

		requestREST("GET",
				"/templates/java/web/project2/rest/resource1/method1",
				undefined, undefined, function(response) {

					jaxbList = response;

					jaxbList.forEach(function(jaxb) {

						$('#table1 tbody').append(
								'<tr><td>' + jaxb.id + '</td><td>' + jaxb.date
										+ '</td><td>' + jaxb.description
										+ '</td></tr>');

					});

					if (callback != undefined) {
						callback();
					}

				}, function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				});

	}

	function getJaxbByIdQuery(id) {

		requestREST("GET",
				"/templates/java/web/project2/rest/resource1/method2?id=" + id,
				undefined, undefined, function(response) {

					$('#table2').append(
							'<tr><td>' + response.id + '</td><td>'
									+ response.date + '</td><td>'
									+ response.description + '</td></tr>');

					if (jaxbList.length > 0) {
						getJaxbByIdPath(jaxbList[Math.floor(Math.random()
								* jaxbList.length)].id);
					}

				}, function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				});

	}

	function getJaxbByIdPath(id) {

		requestREST("GET",
				"/templates/java/web/project2/rest/resource1/method3/" + id,
				undefined, undefined, function(response) {

					$('#table3').append(
							'<tr><td>' + response.id + '</td><td>'
									+ response.date + '</td><td>'
									+ response.description + '</td></tr>');

				}, function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				});

	}

	function putJaxb(name) {

		requestREST("PUT",
				"/templates/java/web/project2/rest/resource1/method4",
				"application/json", {
					"date" : new Date(),
					"description" : name
				}, function(response) {

					getJaxbList();

				}, function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				});

	}

	function postJaxb(id, name) {

		requestREST("POST",
				"/templates/java/web/project2/rest/resource1/method5",
				"application/json", {
					"id" : id,
					"date" : new Date(),
					"description" : name
				}, function(response) {

					getJaxbList();

				}, function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				});

	}

	function deleteJaxb(id) {

		requestREST("DELETE",
				"/templates/java/web/project2/rest/resource1/method6/" + id,
				undefined, undefined, function(response) {

					alert(response);
					getJaxbList();

				}, function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				});

	}

	function requestREST(type, url, contentType, data, cbSuccess, cbError) {

		ajaxContent = {
			type : type,
			url : url,
			success : function(response) {
				cbSuccess(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				cbError(jqXHR, textStatus, errorThrown);
			}
		};

		if (data != undefined)
			ajaxContent.data = JSON.stringify(data);

		if (contentType != undefined)
			ajaxContent.contentType = contentType;

		$.ajax(ajaxContent);

	}

	// Declaração de atributos e métodos públicos:
	this.initialize = initialize;
}

var index = new Index();
index.initialize();