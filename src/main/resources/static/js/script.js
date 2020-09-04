//Permite apenas a digitação de números, validando através da tabla ASCII.
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if((charCode >= 48 && charCode <= 57) || charCode <= 31 || charCode >= 96 && charCode <= 105){
        return true;
    }
    return false;
}

//Gerencia a pesquisa por texto ou por categoria.
function searchRest(categoriaId) {
	
	var t = document.getElementById("searchType");
	
	if (categoriaId == null) {
		t.value = "Texto";
	
	} else {
		t.value = "Categoria";
		document.getElementById("categoriaId").value = categoriaId;
	}
	
	document.getElementById("form").submit();
}

//Gerencia o botão de filtro selecionado na pesquisa.
function setCmd(cmd){
	document.getElementById("cmd").value = cmd;
	document.getElementById("form").submit();
}

function filterCardapio(categoria) {
	document.getElementById("categoria").value = categoria;
	document.getElementById("filterForm").submit();
}