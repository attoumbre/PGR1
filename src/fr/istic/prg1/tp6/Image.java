package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

/**
 * @author Juvenal Attoumbre && Emilie DaConceicao
 * @version 5.0
 * @since 2021-11-22
 *
 *        Classe dÃ©crivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 *
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * @param x
	 *            abscisse du point
	 * @param y
	 *            ordonnÃ©e du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumÃ© dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		int prof = 0;
		int upperX = 0, upperY = 0;
		int largeur = 256;
		int hauteur;

		Iterator<Node> it = this.iterator();
		while (it.nodeType() != NodeType.LEAF) {
			hauteur = largeur / 2;
			if (prof % 2 == 0) {
				if (y < hauteur + upperY) {
					it.goLeft();
				} else {
					upperY += hauteur;
					it.goRight();
				}
			} else {
				largeur = hauteur;
				if (x < upperX + largeur) {
					it.goLeft();
				} else {
					upperX += largeur;
					it.goRight();
				}
			}
			++prof;
		}

		return it.getValue().state == 1;
	}

	/**
	 * this devient identique Ã  image2.
	 *
	 * @param image2
	 *            image Ã  copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {

		Iterator <Node> it2  = image2.iterator();
		Iterator <Node> it  = this.iterator();
		it.clear();
		this.affectAux(it, it2);
	}

	private void affectAux(Iterator<Node> it, Iterator<Node> it2) {
		it.addValue(Node.valueOf(it2.getValue().state));
		if(it2.nodeType() != NodeType.LEAF){
			it.goLeft();
			it2.goLeft();
			this.affectAux(it,it2);

			it.goUp();
			it2.goUp();

			it.goRight();
			it2.goRight();
			this.affectAux(it,it2);

			it.goUp();
			it2.goUp();
		}
	}

	/**
	 * this devient rotation de image2 Ã  180 degrÃ©s.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		Iterator<Node> it2 = image2.iterator();
		Iterator<Node> it1 = this.iterator();

		//Clear this image
		it1.clear();

		if (!it2.isEmpty())
			rotate180Aux(it2, it1);
	}

	private void rotate180Aux(Iterator<Node> it2, Iterator<Node> it1) {

		if (!it2.isEmpty()) {

			int e = it2.getValue().state;
			it1.addValue(Node.valueOf(e));

			it2.goLeft();
			it1.goRight();
			rotate180Aux(it2, it1);
			it2.goUp();
			it1.goUp();
			it2.goRight();
			it1.goLeft();
			rotate180Aux(it2, it1);
			it2.goUp();
			it1.goUp();
		}
	}

	/**
	 * this devient rotation de image2 Ã  90 degrÃ©s dans le sens des aiguilles
	 * d'une montre.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate90(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction non demeandée");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient inverse vidÃ©o de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		Iterator<Node> it = this.iterator();

		//inverse tous les nodes et ses fils
		inverseAux(it);
	}

	private void inverseAux(Iterator<Node> it) {

		if (!it.isEmpty()) {
			Node ns = it.getValue();

			//Process this one
			if (ns.state == 1) {
				it.setValue(Node.valueOf(0));
			}
			else if (ns.state == 0) {
				it.setValue(Node.valueOf(1));
			}

			//Process the sons
			it.goLeft();
			inverseAux(it);
			it.goUp();
			it.goRight();
			inverseAux(it);
			it.goUp();
		}
	}

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2
	 *            image Ã  agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		it.clear();
		int prof = 0;
		this.mirrorVAux(it, image2.iterator(), prof);
	}

	private void mirrorVAux(Iterator<Node> it1, Iterator<Node> it2, int prof) {

		it1.addValue(Node.valueOf(it2.getValue().state));
		if(it2.nodeType() != NodeType.LEAF){
			it2.goLeft();
			if(prof % 2== 1) {
				it1.goLeft();
			}  else {
				it1.goRight();
			}
			this.mirrorVAux(it1, it2, ++prof);

			it2.goUp();
			it1.goUp();
			--prof;

			it2.goRight();
			if(prof % 2 == 1) {
				it1.goRight();
			}else {
				it1.goLeft();
			}
			this.mirrorVAux(it1,it2,++prof);
			it1.goUp();
			it2.goUp();
			--prof;
		}
	}





	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2
	 *            image Ã  agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		it.clear();
		int prof = 0;
		this.mirrorHAux(it, image2.iterator(), prof);
	}

	private void mirrorHAux(Iterator<Node> it1, Iterator<Node> it2, int prof) {
		it1.addValue(Node.valueOf(it2.getValue().state));
		if(it2.nodeType() != NodeType.LEAF ){
			it2.goLeft();
			if(prof % 2 ==0){
				it1.goLeft();
			}else{
				it1.goRight();
			}
			++prof;
			this.mirrorHAux(it1, it2,prof);

			it2.goUp();
			it1.goUp();
			--prof;

			it2.goRight();
			if(prof % 2 == 0){
				it1.goRight();
			}else{
				it1.goLeft();
			}
			++prof;
			this.mirrorHAux(it1, it2, prof);

			it2.goUp();
			it1.goUp();
			--prof;
		}


	}

	/**
	 * this devient quart supÃ©rieur gauche de image2.
	 *
	 * @param image2
	 *            image Ã  agrandir
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction à écrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Le quart supÃ©rieur gauche de this devient image2, le reste de this
	 * devient Ã©teint.
	 *
	 * @param image2
	 *            image Ã  rÃ©duire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction à écrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels
	 * allumÃ©s.
	 *
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 *
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		it.clear();
		this.IntersectionAux(it, image1.iterator(), image2.iterator());
	}

	private void IntersectionAux(Iterator<Node> it1, Iterator<Node> it2, Iterator<Node> it3) {

		if(it2.getValue().state == 0 || it3.getValue().state==0 ){
			it1.addValue(Node.valueOf(0));
		} else if (it2.getValue().state == it3.getValue().state) {
			switch (it2.getValue().state) {
				case 1 : it1.addValue(Node.valueOf(1));
					break;
				case 2 : it1.addValue(Node.valueOf(2));
					it1.goLeft();
					it2.goLeft();
					it3.goLeft();
					this.IntersectionAux(it1,it2, it3);

					int state1 = it1.getValue().state;

					it1.goUp();
					it2.goUp();
					it3.goUp();

					it1.goRight();
					it2.goRight();
					it3.goRight();
					this.IntersectionAux(it1,it2, it3);

					int state2 = it1.getValue().state;

					it1.goUp();
					it2.goUp();
					it3.goUp();
					if(state1 == state2 && state1 != 2) {
						it1.clear();
						it1.addValue(Node.valueOf(state1));
					}
					break;
			}

		}else {
			if(it2.getValue().state == 2){
				this.affectAux(it1,it2);
			}else{
				this.affectAux(it1, it3);
			}
		}

	}

	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumÃ©s.
	 *
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 *
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		Iterator <Node> it = this.iterator();
		it.clear();
		this.unionAux(it, image1.iterator(),image2.iterator());

	}

	private void unionAux(Iterator<Node> it1, Iterator<Node> it2, Iterator<Node> it3) {
		if (it2.getValue().state == 1 || it3.getValue().state == 1) {
			it1.addValue(Node.valueOf(1));

		} else if (it2.getValue().state == it3.getValue().state) {
			switch (it2.getValue().state) {
				case 0:
					it1.addValue(Node.valueOf(0));
					break;
				case 2:
					it1.addValue(Node.valueOf(2));
					it1.goLeft();
					it2.goLeft();
					it3.goLeft();
					this.unionAux(it1, it2, it3);

					int state1 = it1.getValue().state;

					it1.goUp();
					it2.goUp();
					it3.goUp();

					it1.goRight();
					it2.goRight();
					it3.goRight();
					this.unionAux(it1, it2, it3);

					int state2 = it1.getValue().state;

					it1.goUp();
					it2.goUp();
					it3.goUp();
					if (state1 == state2 && state1 != 2) {
						it1.clear();
						it1.addValue(Node.valueOf(state1));
					}

					break;
			}

		} else {
			if (it2.getValue().state == 2) {
				this.affectAux(it1, it2);
			} else {
				this.affectAux(it1, it3);
			}
		}
	}


	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 *
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumÃ©s dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction à écrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}

	/**
	 * @param x1
	 *            abscisse du premier point
	 * @param y1
	 *            ordonnÃ©e du premier point
	 * @param x2
	 *            abscisse du deuxiÃ¨me point
	 * @param y2
	 *            ordonnÃ©e du deuxiÃ¨me point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont reprÃ©sentÃ©s par
	 *         la mÃªme feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction à écrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}

	/**
	 * @param image2
	 *            autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumÃ©s
	 *         false sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		Iterator<Node> itThis = this.iterator();
		Iterator<Node> it2 = image2.iterator();

		if (it2.isEmpty()) {
			return false; // car vide
		} else {
			return isIncludeNodeByNode(itThis, it2);
		}
	}

	private boolean isIncludeNodeByNode (Iterator<Node> itThis, Iterator<Node> it2) {

		boolean result = true;

		if (!itThis.isEmpty() && !it2.isEmpty()) {
			//Arriver au bout de la branche
			if (it2.getValue().equals(Node.valueOf(0)) || itThis.getValue().equals(Node.valueOf(1))) {
				result = false;

				// Descendants existent
			} else {
				itThis.goLeft();
				it2.goLeft();

				if (isIncludeNodeByNode(itThis, it2)) {
					itThis.goUp();
					it2.goUp();
					itThis.goRight();
					it2.goRight();
					result = isIncludeNodeByNode(itThis, it2);
				} else {
					result = false;
				}
				itThis.goUp();
				it2.goUp();
			}
		}
		//fin exploration de l'arbre
		return result;
	}



}
