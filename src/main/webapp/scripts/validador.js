/**
 * Validação de formulário
 * @author Luiz Felipe Grifo
 */

function validar() {
	let nome = frmTarefa.nome.value
	let data = frmTarefa.data.value

	if (nome === "") {
		alert("Preencha o campo nome!")
		frmTarefa.nome.focus()
		return false
	} else if (data === "") {
		alert("Preencha o campo data!")
		frmTarefa.data.focus()
		return false
	} else {
		document.forms["frmTarefa"].submit()
	}
}