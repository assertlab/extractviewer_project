/**
 * Utilitário para manipulação JavaScript do SIVest.
 */

/**
 * Fecha uma determinada caixa de diálogo, quando não há erros de validação no mesmo. 
 * 
 * @author Cleber Alberto
 */
function closeOnSucess(args, popup) {
	if (!args.validationFailed) {
		popup.hide();
	}
}

function preencheuCep(cep, botao) {
	var texto = document.getElementById(cep);
	var valor = texto.value.replace(".", "").replace("-", "").replace("_", "");
	var btn = document.getElementById(botao); 
	if(valor.length != 8){
		btn.disabled = false;
	} else {
		btn.disabled = true;
	}
	btn.refresh();
}

var RecaptchaOptions = {
        custom_translations : {
                instructions_visual : "Escreva as palavras lê:",
                instructions_audio : "Escreva o que ouve:",
                play_again : "Tocar o som novamente",
                cant_hear_this : "Baixe o som em MP3",
                visual_challenge : "Código visual",
                audio_challenge : "Código sonoro",
                refresh_btn : "Tentar outro código",
                help_btn : "Ajuda",
                incorrect_try_again : "Incorreto. Tente novamente.",
        }
};