package GoogleMapsApiResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleMapsApiResponse {
	public String status;
	public List<Route> routes;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Route {
		public List<Leg> legs;
		public OverviewPolyline overviewPolyline;

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class OverviewPolyline {
			public String points;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Leg {
		public Distance distance;
		public Duration duration;

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Distance {
			public String text;
			public int value;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Duration {
			public String text;
			public int value;
		}
	}
}
