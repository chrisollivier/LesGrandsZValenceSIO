#!/usr/bin/python3.7
# -*- coding: utf-8 -*-
#
#		client TCP fait pour communiquer avec un serveur optimisation de la vitesse de transmission des trames
#
#	Auteur : Wilfrid Grassi
#	Date Creation: 08/05/2019
#	Date Modifications : 25/11/2019
#	Version 1.0
#

import socket
import sys
import time

# Python 3 code
print("Version Python : %s" % sys.version_info[0])
print("Ce programme fonctionne sous Python 3")

# Creation d un tuple adresse
adresse=('127.0.0.1', 1000)

#------------------------------------  CLASSE ClientTCP --------------------------------------------------
class ClientTCP:
	#Constructeur Client avec ip et port Serveur
	def __init__(self, IPServeur, PortServeur):
		adresse=(IPServeur, PortServeur)
        # Appel de la méthode StartClient
		self.StartClient(adresse)
	
	#Constructeur Client avec saisie ip et port Serveur
	def __init__(self):
		print("Entrez l Adresse du Serveur :")
		adresseIP = input("")
		print("Entrez le Port du Serveur :")
		Port = int(input(""))
		adresse = (adresseIP,Port)	
		# Appel de la méthode StartClient
		self.StartClient(adresse)

	#Methode principale du Client
	def StartClient(self,adresse):
		RunClient = True
		# Creation d un socket de connexion
		monSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		try:
			# Etablissement de la connexion sur le socket
			monSocket.connect(adresse)
			print("Client connecte")
			RunClient = True
		except:
			print("impossible d'etablir la connexion !!!")
			RunClkient = False

		# Boucle principale du client de saisie et envoi de la requete vers le serveur
		while RunClient:
			requeteSaisie = None
			# Saisie au clavier de la requete
			#requeteSaisie = input("Saisir la requete a envoyer: ")		
            # !!!! Encoder la trame de bytes en utf-8, ne pas oublier de la decoder en utf-8 lors de la reception
			#requete = bytes(requeteSaisie, 'utf-8')

			'''if requeteSaisie=="":
				print("Aucune requete envoyee !")
			else:
				try:
					print("Requete envoyee => %s" % (requeteSaisie,))
					# Envoi de la requete
					envoi = monSocket.send(requete)
				except:
					print("Le serveur n est plus accessible !!! Il a sans soute ete arrete...")
					RunClient = False'''
			TrameRecue = monSocket.recv(100)
			print(TrameRecue.decode("utf-8"))
		# Fermeture du client			
		print("Fermeture du client!")
		monSocket.close()
		
#---------------------------------- Main ----------------------------------------------------#
# Creation d une instance de la Classe ClientTCP
monClientTCP = ClientTCP()