﻿using SiteInterfaces;
using WeatherTools;

namespace Task6
{
    public static class PrintTools
    {
        public static void PrintForecast(List<ISite> sites)
        {
            foreach (var site in sites)
            {
                Weather? weather = site.WeatherInfo;
                PrintCentrally($"Data is loaded from the site with the address {site.SiteAddress}");
                PrintCentrally("Checking for errors...");
                if (site.ExceptionMessages.Equals("No errors detected."))
                {
                    Console.ForegroundColor = ConsoleColor.Green;
                }
                else
                {
                    Console.ForegroundColor = ConsoleColor.Red;
                }
                PrintCentrally(site.ExceptionMessages);
                Console.ResetColor();
                if (weather is null)
                {
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("Unfortunately, there is no weather data.");
                    Console.ResetColor();
                }
                else
                {
                    Console.WriteLine($"Temperature => {Math.Round(weather.TemperatureInCelsius)} °C | {Math.Round(weather.TemperatureInFahrenheit)} °F");
                    Console.WriteLine($"Cloud cover => {weather.CloudCover} %");
                    Console.WriteLine($"Humidity => {weather.Humidity} %");
                    Console.WriteLine($"Precipitation => {weather.Precipitation}");
                    Console.WriteLine($"Wind direction => {weather.WindDirection} deg.");
                    Console.WriteLine($"Wind speed => {weather.WindSpeed} m/s");
                }
                Console.WriteLine();
            }
        }

        public static bool PrintCentrally(string text, int width = 100)
        {
            bool error = false;
            try
            {
                width = Console.WindowWidth;
            }
            catch (Exception)
            {
                error = true;
            }
            var padding = width / 2 + text.Length / 2;
            Console.WriteLine("{0," + padding + "}", text);
            return !error;
        }
    }
}