package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	// Construtor
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		ConfiguracaoInicial();
	}

	// M�todo
	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public PecaDeXadrez executarMovimentoDeXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoDeOrigem(origem);
		Peca capturarPeca = fazerMover(origem, destino);
		return (PecaDeXadrez) capturarPeca;
	}

	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturarPeca = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		return capturarPeca;
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezException("N�o existe pe�a na posi��o de origem.");
		}
	}

	private void coloqueUmaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
	}

	// inicia a partida de xadrez colocando as pecas no tabuleiro.
	private void ConfiguracaoInicial() {

		coloqueUmaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		coloqueUmaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
