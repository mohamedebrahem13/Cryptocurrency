
# Cryptocurrency
2
that lets users to get data from api https://api.coinpaprika.com thats return list of crypto coin and get the Coin Detail by id Techniques Used in this app
3
clean architecture mvvm using 3 layer data layer and domain layer and presentation layer and using  dagger hilt for ndency injection and retrofit to get the data from api and kotlin coutines to get the list of coin in background thread and using kotlin flow to colect data as flow because we need to emit multiply value in the same time we are using class for that called Resource it's sealed cals thats  has 3 value for the state of the call is Success or error or is Loading and we are usnig  this calss in use case as flow of Resource and using retry fun to retry the  call if it faile and keep tring for 10 times and if it's failed for 10 we emit Resource.Error() with error message and using data bnding to bind data and views to and from xml and using binding adapter to bind complex data to xml   
