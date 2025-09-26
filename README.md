<article id="detailed-description">
  <header>
    <h1>Flight Companion — Your Travel Assistant for International Flights</h1>
    <p>Flight Companion is an Android app built to help Vietnamese seniors travel internationally with confidence — even with little or no English. It combines clear flight information, practical bilingual phrases, and non-verbal communication tools to make every step of the journey easier and safer.</p>
  </header>

  <section>
    <h2>Overview</h2>
    <p>This app delivers essential flight details and contextual language assistance tailored for older passengers. The design prioritizes readability and simplicity: large fonts, clear buttons, and a minimal layout that reduces confusion. Because many elders find public Wi-Fi or in-flight connections difficult, Flight Companion is fully functional offline.</p>
  </section>

  <section>
    <h2>Core Features</h2>
    <ul>
      <li><strong>Flight information:</strong> Store and view flight number, airline, departure/arrival times, gate, and seat details in one place.</li>
      <li><strong>Bilingual phrase library:</strong> Common airport and in-flight sentences presented in Vietnamese and English for quick reference.</li>
      <li><strong>Non-verbal meal & crew communication:</strong> Visual prompts and ready-made messages allow passengers to request meals or services without speaking English directly.</li>
      <li><strong>Text-to-speech (TTS):</strong> Hear phrases spoken aloud to assist with pronunciation and facilitate communication with staff.</li>
      <li><strong>Offline operation:</strong> All core functions — phrase lookup, TTS playback, and stored flight/personal data — work without network access.</li>
      <li><strong>Reusable profiles:</strong> Personal information and flight templates are stored separately so they can be reused for future trips.</li>
      <li><strong>Senior-friendly UI:</strong> High-contrast elements, larger touch targets, and simplified navigation optimized for older users.</li>
    </ul>
  </section>

  <section>
    <h2>Safety & Emergency Support</h2>
    <p>Personal and flight information is kept locally on the device. In an emergency, caregivers or staff can quickly access key passenger details to provide assistance. The app is designed to make information available fast and clearly, improving response times when every second matters.</p>
  </section>

  <section>
    <h2>Technical Details</h2>
    <p>Flight Companion is implemented in Kotlin using Jetpack Compose. Key architectural and library choices include:</p>
    <ul>
      <li>Data serialization and APIs via Protocol Buffers (proto).</li>
      <li>Local persistence with DataStore for reliable offline storage.</li>
      <li>ViewModel for state management and smooth screen-to-screen data flow.</li>
      <li>Offline translation support using Google’s on-device translation libraries for automatic phrase suggestions.</li>
      <li>Built-in TTS for natural spoken guidance.</li>
    </ul>
  </section>

  <section>
    <h2>Why it helps older travelers</h2>
    <p>Many elders feel anxious about international travel because of language barriers and unfamiliar procedures. Flight Companion focuses on:</p>
    <ul>
      <li>Lowering stress with simple, clear instructions and auditory help.</li>
      <li>Allowing independence by providing non-verbal options to communicate with crew.</li>
      <li>Eliminating reliance on airport Wi-Fi — everything works offline.</li>
    </ul>
  </section>

  <section>
    <h2>Get started</h2>
    <ol>
      <li>Install the app on an Android device.</li>
      <li>The downloading of offline translator  will be auto in background</li>
      <li>Open -> Trong tin ca nhan -> Sua Thong Tin → Enter personal profile and emergency contact information.</li>
      <li>Add flight details in Setting in main Screen</li>
      <li>Use the phrase library and TTS to prepare for the journey.</li>
    </ol>
  </section>

  <footer>
    <p>Built with care for seniors and designed for real travel scenarios. For technical notes, contributions, or support, please refer to the project repository or contact the development team.</p>
  </footer>
</article>
