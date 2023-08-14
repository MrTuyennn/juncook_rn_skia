import {SafeAreaProvider} from 'react-native-safe-area-context';
import {Block, Text} from './components';
import {
  NativeModules,
  StyleSheet,
  TouchableOpacity,
  NativeEventEmitter,
} from 'react-native';
import {useTheme} from './theme';
import {NavigationContainer} from '@react-navigation/native';
import {useEffect, useState} from 'react';

const CounterEvents = new NativeEventEmitter(NativeModules.Counter);

const App = () => {
  const {NavigationTheme} = useTheme();

  const [count, setCount] = useState(0);

  useEffect(() => {
    CounterEvents.addListener('onIncrement', result =>
      console.log('onIncrement received', result),
    );
    CounterEvents.addListener('onDecrement', result =>
      console.log('onDecrement received', result),
    );

    return () => {
      CounterEvents.removeAllListeners();
    };
  }, []);

  const increment = () => {
    NativeModules.Counter.increment((value: any) => {
      setCount(value);
    });
  };

  const decrement = async () => {
    try {
      var result = await NativeModules.Counter.decrement();
      console.log('result', result);
    } catch (e: any) {
      console.log(e.message, e.code);
    }
  };

  return (
    <NavigationContainer theme={NavigationTheme}>
      <SafeAreaProvider>
        <Block flex={1} justify="center" align="center">
          <Text>{count}</Text>
          <TouchableOpacity style={styles.buttonContainer} onPress={increment}>
            <Text color={'white'}>Cộng nè</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.buttonContainer} onPress={decrement}>
            <Text color={'white'}>Trừ nè</Text>
          </TouchableOpacity>
        </Block>
      </SafeAreaProvider>
    </NavigationContainer>
  );
};

export default App;

const styles = StyleSheet.create({
  buttonContainer: {
    height: 40,
    width: 100,
    backgroundColor: 'red',
    justifyContent: 'center',
    alignItems: 'center',
    marginVertical: 20,
  },
});
