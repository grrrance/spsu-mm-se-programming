package view;

import model.WeatherData.WeatherData;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherMapViewTest {
	@Test
	void outputData() {
		OpenWeatherMapView view = new OpenWeatherMapView(new WeatherData(1., 2., 3., 4., 5., 6., 7.));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream newPrintStream = new PrintStream(byteArrayOutputStream);
		System.setOut(newPrintStream);

		view.outputData();
		assertEquals(
				"OpenWeatherMap" + System.lineSeparator()
						+ "Air Temperature, C\u00B0: 1.0" + System.lineSeparator()
						+ "Air Temperature, F\u00B0: 2.0" + System.lineSeparator()
						+ "Cloud Cover, %: 3.0" + System.lineSeparator()
						+ "Humidity, %: 4.0" + System.lineSeparator()
						+ "Precipitation, mm/h: 5.0" + System.lineSeparator()
						+ "Wind Direction, \u00B0: 6.0" + System.lineSeparator()
						+ "Wind Speed, m/s: 7.0" + System.lineSeparator(),
				byteArrayOutputStream.toString().replaceAll("\\n|\\r\\n", System.lineSeparator())
		);
	}

	@Test
	void outputDataWithNulls() {
		OpenWeatherMapView view = new OpenWeatherMapView(new WeatherData(1., null, 3., null, 5., 6., 7.));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream newPrintStream = new PrintStream(byteArrayOutputStream);
		System.setOut(newPrintStream);

		view.outputData();
		assertEquals(
				"OpenWeatherMap" + System.lineSeparator()
						+ "Air Temperature, C\u00B0: 1.0" + System.lineSeparator()
						+ "Air Temperature, F\u00B0: Data unavailable :(" + System.lineSeparator()
						+ "Cloud Cover, %: 3.0" + System.lineSeparator()
						+ "Humidity, %: Data unavailable :(" + System.lineSeparator()
						+ "Precipitation, mm/h: 5.0" + System.lineSeparator()
						+ "Wind Direction, \u00B0: 6.0" + System.lineSeparator()
						+ "Wind Speed, m/s: 7.0" + System.lineSeparator(),
				byteArrayOutputStream.toString().replaceAll("\\n|\\r\\n", System.lineSeparator())
		);
	}
}