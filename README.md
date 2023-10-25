  # Weather service
  Provide metrics based on weather data extracted from weather providers.


### Notes
   The daily summary API call is now behind paywall.  I've used API which returns 3-hour Forecast for next 5 days.  THis required additional logic to summarize by day.  
  
### Remaining work
* Robust error handing and re-try logic if OpenWeather provider is transiently unavailable. Just need to configure Feign correctly once we decide on failure pattern (eg back pressure or fail fast or fail over to alternate provider)