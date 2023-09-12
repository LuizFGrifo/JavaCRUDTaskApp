/**
 * Confirmação de exclusão de contato
 * @author Luiz Felipe Grifo
 */

 function confirmar(idtar) {
	 let resposta = confirm("Confirmar a exclusão desta tarefa ?")
	 if(resposta === true) {
		 //alert(idtar)
		 window.location.href = "delete?idtar=" + idtar
	 }
 }