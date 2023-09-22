import {NavigationContainer} from '@react-navigation/native';
import {SafeAreaProvider} from 'react-native-safe-area-context';
import {useTheme} from './theme';

const App = () => {
  const {NavigationTheme} = useTheme();

  return (
    <NavigationContainer theme={NavigationTheme}>
      <SafeAreaProvider></SafeAreaProvider>
    </NavigationContainer>
  );
};

export default App;
