Desafio DSMovie RestAssured

----Sobre o projeto DSMovie:

Este é um projeto de filmes e avaliações de filmes onde coloquei em prática meus conhecimentos de testes de API com REST ASSURED.
A visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar)
são permitidas apenas para usuários ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. 


![DsMovie](https://github.com/DennerOl/DESAFIO-DSMovie-RestAssured/assets/124217386/5ad36723-e699-4679-af61-dd443b30567f)



----Endpoints que apliquei os testes de API com RestAssured. 


---MovieContollerRA:

findAllShouldReturnOkWhenMovieNoArgumentsGiven

findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty

findByIdShouldReturnMovieWhenIdExists

findByIdShouldReturnNotFoundWhenIdDoesNotExist

insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle

insertShouldReturnForbiddenWhenClientLogged

insertShouldReturnUnauthorizedWhenInvalidToken


---ScoreContollerRA:

saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist

saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId

saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero





