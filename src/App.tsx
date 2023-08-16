import {SafeAreaProvider} from 'react-native-safe-area-context';
import {Block, Text} from './components';
import {
  NativeModules,
  StyleProp,
  StyleSheet,
  TouchableOpacity,
  View,
  ViewStyle,
  requireNativeComponent,
} from 'react-native';
import {useTheme} from './theme';
import {NavigationContainer} from '@react-navigation/native';
import {useEffect, useState} from 'react';
interface ExoPlayerViewProps {
  style?: StyleProp<ViewStyle>;
}
const ExoPlayer = requireNativeComponent<ExoPlayerViewProps>('ExoPlayer');

const App = () => {
  const {NavigationTheme} = useTheme();

  const [count, setCount] = useState(0);

  useEffect(() => {}, []);

  console.log('ExoPlayer', ExoPlayer);

  const increment = () => {
    NativeModules.Counter.increment((value: any) => {
      setCount(value);
    });
  };

  const decrement = async () => {
    try {
      var result = await NativeModules.Counter.decrement();
      setCount(result);
    } catch (e: any) {
      console.log(e.message, e.code);
    }
  };

  return (
    <NavigationContainer theme={NavigationTheme}>
      <SafeAreaProvider>
        <Block backgroundColor="white" flex={1} justify="center" align="center">
          <View>
            <ExoPlayer
              style={{
                height: 100,
                width: 100,
                backgroundColor: 'red',
              }}
            />
          </View>
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
