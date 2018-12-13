var gulp 			= require('gulp');
var less            = require('gulp-less');
var sourcemaps      = require('gulp-sourcemaps');
var cleanCSS        = require('gulp-clean-css');
var uglify 			= require('gulp-uglify');
var concat 			= require('gulp-concat');
var flatten         = require('gulp-flatten');
var imagemin        = require('gulp-imagemin');
var gutil           = require('gulp-util');
var runSequence     = require('run-sequence');
var shell           = require('gulp-shell');
var pump            = require('pump');
var rename          = require("gulp-rename");

var theme_url = 'src/main/resources/static';

var paths = {
    style: theme_url + '/less/style.less',
    less: theme_url + '/less',
    
};

gulp.task('styles', function()
{
    return gulp.src(paths.style)
        .pipe(sourcemaps.init())
        .pipe(less())
        .pipe(gutil.env.type === 'prod' ? cleanCSS({compatibility: 'ie8'}) : gutil.noop())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest(theme_url + '/css'));
});

// watch for less and script files
gulp.task('watch', function()
{
    gulp.watch(paths.less + '/**/*.less', ['styles']);

});


// default task run all the tasks
gulp.task('default', function(callback){
    runSequence('styles', callback);
});
